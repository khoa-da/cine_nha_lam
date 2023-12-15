import React from "react";
import "./Slider.scss";

import {
  Navigation,
  Pagination,
  Scrollbar,
  A11y,
  Autoplay,
} from "swiper/modules";
import { Swiper, SwiperSlide } from "swiper/react";

import "swiper/css";
import "swiper/css/navigation";
import "swiper/css/pagination";
import "swiper/css/scrollbar";

import asset from "../../Assets";

function Slider() {
  return (
    <Swiper
      // install Swiper modules
      modules={[Navigation, Pagination, Scrollbar, A11y, Autoplay]}
      spaceBetween={50}
      slidesPerView={1}
      navigation
      pagination={{ clickable: true }}
      scrollbar={{ draggable: true }}
      onSwiper={(swiper) => console.log(swiper)}
      onSlideChange={() => console.log("slide change")}
      autoplay={{ delay: 5000, disableOnInteraction: false }}
      loop
    >
      <SwiperSlide>
        <img src={asset.slider1} alt="slider1" />
      </SwiperSlide>
      <SwiperSlide>
        <img src={asset.slider2} alt="slider2" />
      </SwiperSlide>
      <SwiperSlide>
        <img src={asset.slider3} alt="slider3" />
      </SwiperSlide>
      <SwiperSlide>
        <img src={asset.slider4} alt="slider4" />
      </SwiperSlide>
      <SwiperSlide>
        <img src={asset.slider5} alt="slider5" />
      </SwiperSlide>
      <SwiperSlide>
        <img src={asset.slider6} alt="slider6" />
      </SwiperSlide>
      <SwiperSlide>
        <img src={asset.slider7} alt="slider7" />
      </SwiperSlide>
    </Swiper>
  );
}
export default Slider;
