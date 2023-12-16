import React from "react";
import "./Slider.scss";
import { useState } from "react";
import { useEffect } from "react";
import SliderAPI from "../../Api/SliderAPI";

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
  const [sliderList, setSliderList] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await SliderAPI.getAll();
        console.log(response.listResult);

        const updatedSliderList = [
          {
            sliderImageURL: asset.slider1,
          },
          {
            sliderImageURL: asset.slider3,
          },
          {
            sliderImageURL: asset.slider4,
          },
          ...response.listResult,
        ];

        setSliderList(updatedSliderList);
      } catch (error) {
        console.error("Error fetching slider data:", error);
      }
    };

    fetchData();
  }, []);

  // Nếu sliderList rỗng, thêm một slider mặc định vào
  useEffect(() => {
    if (sliderList.length === 0) {
      setSliderList([
        {
          sliderImageURL: asset.slider1,
        },
        {
          sliderImageURL: asset.slider3,
        },
        {
          sliderImageURL: asset.slider4,
        },
        // Thêm các hình ảnh mặc định khác nếu cần
      ]);
    }
  }, [sliderList]);

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
      autoplay={{ delay: 3000, disableOnInteraction: false }}
      loop
    >
      {/* <SwiperSlide>
        <img src={asset.slider1} alt="slider1" />
      </SwiperSlide>
      <SwiperSlide>
        <img src={asset.slider3} alt="slider3" />
      </SwiperSlide>
      <SwiperSlide>
        <img src={asset.slider4} alt="slider4" />
      </SwiperSlide>
      <SwiperSlide>
        <img src={asset.slider5} alt="slider5" />
      </SwiperSlide> */}

      {sliderList.map((slider, index) => (
        <SwiperSlide key={index}>
          <img src={slider.sliderImageURL} alt={"slider${index + 1}"} />
        </SwiperSlide>
      ))}
    </Swiper>
  );
}
export default Slider;
