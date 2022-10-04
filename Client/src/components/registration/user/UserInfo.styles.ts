import styled from "@emotion/styled";

export const UserInfoContainer = styled.div`
  width: 30%;
  padding: 1rem;

  display: flex;
  flex-direction: column;
  justify-content: center;

  /* background: #ffe8cc; */
  background: #fff;
  // background: linear-gradient(-45deg, #ffe8cc, #ffd8a8);
`;

export const Block = styled.div`
  position: relative;
`;

export const UserName = styled.div`
  font-size: 2rem;
  font-family: "GangwonEdu_OTFBoldA";
  margin-bottom: 20px;
`;

export const BirdName = styled.div`
  font-size: 1.2rem;
  font-family: "GangwonEdu_OTFBoldA";
  margin-bottom: 30px;
`;

export const Images = styled.div`
  position: absolute;
  top: 0;
  left: 3rem;
`;

export const Bird = styled.img`
  width: 220px;
  margin-bottom: 2rem;
`;

export const Person = styled.img`
  height: 400px;
`;

export const Meta = styled.div`
  padding-top: 360px;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 1rem;
`;

export const Circles = styled.div`
  display: flex;
  justify-content: space-between;
`;

export const CircleWrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

export const Circle = styled.div`
  width: 4rem;
  height: 4rem;
  border-radius: 50%;
  background: #ccc;
`;
