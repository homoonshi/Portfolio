import feedmeImg from '../assets/tempImg/feedme.gif';
import meojeonImg from '../assets/tempImg/meojeonTrip.png';
import sapiImg from '../assets/tempImg/SAPI.jpg';
import trip97Img from '../assets/tempImg/trip97.png';
import tripletImg from '../assets/tempImg/triplet.png';
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import Slider from "react-slick";

export const MainPage = () => {
    const settings = {
        dots: true,
        infinite: true,
        speed: 500,
        slidesToShow: 1,
        slidesToScroll: 1,
        autoplay: true,
        autoplaySpeed: 3000,
    };

    const images = [feedmeImg, meojeonImg, sapiImg, trip97Img, tripletImg];

    return (
    <div className="w-full h-full flex items-center justify-center">
        <div className="w-[800px] rounded-xl flex-col items-center justify-center">
            <Slider {...settings}>
                {images.map ((img, idx) => (
                    <div key={idx} className="w-full h-[440px] flex items-center justify-center">
                        <img src={img} alt={`slide-${idx}`} className="w-full object-contain h-full" />
                    </div>
                ))}
            </Slider>
        </div>
    </div>
    );
}