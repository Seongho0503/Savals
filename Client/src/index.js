import React from "react";
import ReactDOM from "react-dom";
import "./index.css";
import App from "./App";
import "swiper/css/bundle";

import reportWebVitals from "./reportWebVitals";
import {
    BrowserRouter,
    Routes,
    Route,
} from "react-router-dom";
import Home from "./pages/Home";
import Create from "./pages/Create";
import Explore from "./pages/Explore";
import Minting from "./pages/Minting";

//dapp
import { DAppProvider } from "@usedapp/core";
import NftDetail from "./pages/NftDetail";

ReactDOM.render(
    <BrowserRouter>
        <Routes>
            <Route
                path="/"
                element={
                    <DAppProvider
                        config={{}}
                    >
                        <Home />
                    </DAppProvider>
                }
            />
            <Route
                path="/create"
                element={
                    <DAppProvider>
                        <Create />
                    </DAppProvider>
                }
            />
            <Route
                path="/explore"
                element={
                    <DAppProvider
                        config={{}}
                    >
                        <Explore />
                    </DAppProvider>
                }
            />

            <Route
                path="/detail"
                element={
                    <DAppProvider
                        config={{}}
                    >
                        <NftDetail />
                    </DAppProvider>
                }
            />
            <Route
                path="/minting"
                element={
                    <DAppProvider
                        config={{}}
                    >
                        <Minting />
                    </DAppProvider>
                }
            />
        </Routes>
    </BrowserRouter>,
    document.getElementById(
        "root"
    )
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
