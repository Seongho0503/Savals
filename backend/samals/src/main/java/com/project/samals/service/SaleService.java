package com.project.samals.service;

import com.project.samals.domain.Nft;
import com.project.samals.domain.Sale;
import com.project.samals.domain.SaleLike;
import com.project.samals.domain.User;
import com.project.samals.dto.SaleDto;
import com.project.samals.dto.request.ReqSaleCompleteDto;
import com.project.samals.dto.request.ReqSaleDto;
import com.project.samals.dto.response.ResSaleDetailDto;
import com.project.samals.dto.response.ResSaleListDto;
import com.project.samals.repository.NftRepository;
import com.project.samals.repository.SaleLikeRepository;
import com.project.samals.repository.SaleRepository;
import com.project.samals.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SaleService {

    private final SaleRepository saleRepository;
    private final SaleLikeRepository saleLikeRepository;
    private final UserRepository userRepository;
    private final NftRepository nftRepository;

    private final SaleLikeService saleLikeService;
    //거래 등록
    @Transactional
    public SaleDto createSale(ReqSaleDto saleDto) {
        Nft nft = nftRepository.findByTokenId(saleDto.getTokenId());
        Sale sale =saleDto.toEntity(nft);
        saleRepository.save(sale);
        nft.addNftSaleList(sale);
        nftRepository.save(nft);
        return SaleDto.convert(sale);
    }

    public List<ResSaleListDto> getSaleList(String animalSpecies,String address){
        User user = userRepository.findByWalletAddress(address);
        List<ResSaleListDto> saleList = new ArrayList<>();
        for(Sale sale : saleRepository.findAllByIsSold('N')){
            ResSaleListDto saleDto = ResSaleListDto.convert(sale);
            SaleLike saleLike = saleLikeRepository.findBySaleAndUser(sale,user);
            if(saleLike!= null)
                saleDto.setLikePush('Y');
            else
                saleDto.setLikePush('N');
            saleDto.setLikeCount(saleLikeService.getSaleLikeCount(sale.getSaleSeq()));
            if(animalSpecies!=null&&saleDto.getAnimalSpecies().equals(animalSpecies))
                saleList.add(saleDto);
            else if(animalSpecies==null)
                saleList.add(saleDto);
        }
        return saleList;
    }

    public ResSaleDetailDto getSale(long saleSeq) {
        Sale saleInfo=saleRepository.findBySaleSeq(saleSeq);
        return ResSaleDetailDto.convert(saleInfo);
    }

    public SaleDto completeSale(ReqSaleCompleteDto reqSaleCompleteDto) {
        Sale sale = saleRepository.findBySaleSeq(reqSaleCompleteDto.getSaleSeq());
        sale.setIsSold('Y');
        sale.setCompletedTime(new Date());
        sale.setBuyerAddress(reqSaleCompleteDto.getBuyerAddress());
        sale.getNft().setNftOwner(reqSaleCompleteDto.getBuyerAddress());
        sale.getNft().setUpdatedTime(new Date());

        Sale saved = saleRepository.save(sale);
        return SaleDto.convert(saved);
    }

    //내 거래내역 (판매 + 구매)
    public List<SaleDto> getMySaleList(String address){
        List<SaleDto> saleList = new ArrayList<>();
        for(Sale sale : saleRepository.findAllBySellerAddress(address)){
            saleList.add(SaleDto.convert(sale));
        }
        for(Sale sale : saleRepository.findAllByBuyerAddress(address)){
            saleList.add(SaleDto.convert(sale));
        }
        /*
        Todo
        1. pfp 번호 추가하기
        2. 최근 10개 목록만 불러오기
        3. 생성 시간별로 정렬
         */

        return saleList;
    }

    public List<ResSaleListDto> search(String search, String address){
        User user = userRepository.findByWalletAddress(address);
        List<ResSaleListDto> saleList = new ArrayList<>();
        for(Sale sale : saleRepository.findAllByIsSoldAndSaleTitleContainingIgnoreCase('N',search)){
            ResSaleListDto saleListDto = ResSaleListDto.convert(sale);
            SaleLike saleLike = saleLikeRepository.findBySaleAndUser(sale,user);
            if(saleLike!= null)
                saleListDto.setLikePush('Y');
            else
                saleListDto.setLikePush('N');
            saleListDto.setLikeCount(saleLikeService.getSaleLikeCount(sale.getSaleSeq()));
            saleList.add(saleListDto);
        }
        return saleList;

    }

}
