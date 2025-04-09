import React from "react";
import { Route,
    createBrowserRouter,
    createRoutesFromElements,
    RouterProvider
 } from "react-router-dom"

const router = createBrowserRouter (
    createRoutesFromElements(
        <Route element={}>
            <Route path="/" element={</>} />
        
        </Route>
    )
)

const AppRoute: React.FC = () => {

    return <RouterProvider router={router}/>;

};


export default AppRoute;