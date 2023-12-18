import React from "react";
import "./ButtonLoginRegister.scss";
import { NavLink } from "react-router-dom";
import asset from "../../Assets";

function ButtonLoginRegister() {
  return (
    <div className="button-login-register-container">
      <div className="button-login-register-inner">
        <div className="register-item">
          <img className="img-register" src={asset.iconRegister} />
          <NavLink to="/register">ĐĂNG KÝ THÀNH VIÊN</NavLink>
        </div>
        <div className="login-item">
          <img className="img-login" src={asset.iconLogin} />
          <NavLink to="/login">ĐĂNG NHẬP</NavLink>
        </div>
      </div>
      <div className="call">
        <img className="img-call" src={asset.iconCall} />
        <NavLink to="tel:0943488056">0943 488 056</NavLink>
      </div>
    </div>
  );
}

export default ButtonLoginRegister;
