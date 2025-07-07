import React from "react";
import {
    createBrowserRouter,
    RouterProvider
} from "react-router-dom"
import App from "../App";
import { MainPage } from "../pages/MainPage";
import { LoginPage } from "../pages/login/LoginPage";

const loginUrl = import.meta.env.VITE_LOGIN_URL;

const router = createBrowserRouter ([
    {
        path : "/",
        Component : App,
        children: [
            { path: "", Component: MainPage},
            { path: loginUrl!, Component: LoginPage}
        ],
    },
])

export const AppRoute = () => {
    return <RouterProvider router={router}/>;
};

