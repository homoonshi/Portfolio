import { useState } from "react"
import { Link } from "react-router-dom";

export const Header = () => {

    const [menu1, setMenu1] = useState(false);
    const [menu2, setMenu2] = useState(false);

    return (
        <div className="w-full h-18 flex flex-row  bg-white">
            <Link to="" className="basis-full text-2xl flex items-center pl-10">
                Portfolio
            </Link>
            <div className="basis-full flex items-center justify-center text-xl relative"
                onMouseOver={() => setMenu1(true)}
                onMouseLeave={() => setMenu1(false)}
            >
                Info
                <div className={`absolute top-16 left-0 w-full p-4 bg-white transition-all ease-in-out z-[9999]
                    ${menu1 ? "opacity-100 translate-y-0 visible" : "opacity-0 -translate-y-2 invisible"}`}>
                    <div className="w-full flex flex-col items-center">
                        <div className="p-1">
                            서브메뉴1
                        </div>
                        <div className="p-1">
                            서브메뉴2
                        </div>
                    </div>
                </div>
            </div>
            <div className="basis-full flex items-center justify-center text-xl relative"
            >
                Skill
            </div>
            <div className="basis-full flex items-center justify-center text-xl relative"
                onMouseOver={() => setMenu2(true)}
                onMouseLeave={() => setMenu2(false)}
            >
                Project
                <div className={`absolute top-16 left-0 w-full p-4 bg-white transition-all ease-in-out z-[9999]
                    ${menu2 ? "opacity-100 translate-y-0 visible" : "opacity-0 -translate-y-2 invisible"}`}>
                    <div className="w-full flex flex-col items-center">
                        <div className="p-1">
                            서브메뉴3
                        </div>
                        <div className="p-1">
                            서브메뉴4
                        </div>
                    </div>
                </div>
            </div>
            <div className="basis-full flex items-center justify-center text-xl relative"
            >
                Contact
            </div>
        </div>
    )

}