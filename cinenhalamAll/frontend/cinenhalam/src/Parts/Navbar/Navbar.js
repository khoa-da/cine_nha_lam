import React, { useState } from "react";
import "./Navbar.scss";
import { Link, NavLink } from "react-router-dom";
import { CiSearch } from "react-icons/ci";

import asset from "../../Assets";

function Navbar() {
  const [language, setLanguage] = useState("VI");
  const handleLanguage = () => {
    if (language === "VI") {
      setLanguage("EN");
    } else {
      setLanguage("VI");
    }
  };
  return (
    <div className="header-container">
      <div className="logo">
        <img src={asset.logoxoaphong} alt="logoxoaphone" />
      </div>

      <div className="topnav-container">
        <div className="topnav-search-language">
          <div className="topnav-input-search">
            <input
              className="topnav-search"
              type="text"
              placeholder="Tìm kiếm..."
            />
            <button className="topnav-submit">
              <CiSearch size="30px" />
            </button>
          </div>
          <div className="topnav-language" onClick={() => handleLanguage()}>
            {language}
          </div>
        </div>
        <div className="topnav-border">
          <div className="topnav">
            <NavLink exact to="/" activeClassName="active">
              PHIM
            </NavLink>
            <NavLink to="/calendar">LỊCH CHIẾU</NavLink>
            <NavLink to="/cinema">RẠP</NavLink>
            <NavLink to="/promotion">KHUYẾN MÃI</NavLink>
            <NavLink to="/answer">HỎI ĐÁP</NavLink>
            <NavLink to="/news">TIN TỨC</NavLink>
            <NavLink to="/introduce">GIỚI THIỆU</NavLink>
            <NavLink to="/contact">LIÊN HỆ</NavLink>
          </div>
        </div>
      </div>
    </div>
  );
}
export default Navbar;
