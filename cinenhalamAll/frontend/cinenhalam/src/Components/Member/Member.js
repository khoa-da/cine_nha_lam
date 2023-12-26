import React from "react";

import { Navigation, Pagination, Scrollbar, A11y } from "swiper/modules";
import { Swiper, SwiperSlide } from "swiper/react";

import "swiper/css";
import "swiper/css/navigation";
import "swiper/css/pagination";
import "swiper/css/scrollbar";

import asset from "../../Assets";

import "./Member.scss";

function Member() {
  return (
    // <div className="member-container">
    //   <div className="member-inner">
    //     <label className="member-text">THÀNH VIÊN</label>
    //     <div className="member-item">
    //       <div className="member-copper">
    //         <img src={asset.memberEx} alt="membercart" />
    //       </div>
    //       <div className="member-silver">
    //         <img src={asset.memberEx} alt="membercart" />
    //       </div>
    //       <div className="member-gold">
    //         <img src={asset.memberEx} alt="membercart" />
    //       </div>
    //       <div className="member-premium">
    //         <img src={asset.memberEx} alt="membercart" />
    //       </div>
    //     </div>
    //   </div>
    // </div>
    <div className="member-container">
      <label className="member-text">THÀNH VIÊN</label>
      <Swiper
        // install Swiper modules
        modules={[Navigation, Pagination, Scrollbar, A11y]}
        spaceBetween={50}
        slidesPerView={3}
        navigation
        pagination={{ clickable: true }}
        scrollbar={{ draggable: true }}
        onSwiper={(swiper) => console.log(swiper)}
        onSlideChange={() => console.log("slide change")}
      >
        <SwiperSlide>
          <img className="member-img" src={asset.memberEx} alt="slider1" />
        </SwiperSlide>
        <SwiperSlide>
          <img className="member-img" src={asset.memberEx} alt="slider3" />
        </SwiperSlide>
        <SwiperSlide>
          <img className="member-img" src={asset.memberEx} alt="slider4" />
        </SwiperSlide>
        <SwiperSlide>
          <img className="member-img" src={asset.memberEx} alt="slider5" />
        </SwiperSlide>
      </Swiper>
    </div>
  );
}
export default Member;
