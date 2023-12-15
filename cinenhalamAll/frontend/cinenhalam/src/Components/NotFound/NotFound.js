// NotFound.js

import React from "react";
import "./NotFound.scss";
import { Link, NavLink } from "react-router-dom";
import Navbar from "../../Parts/Navbar/Navbar";

function NotFound() {
  return (
    <div>
      <Navbar />
      <div className="container">
        <div className="heading">404 Not Found</div>
        <div className="message">
          Oops! It seems like the page you are looking for does not exist.
        </div>
        <div className="message">
          Go back to{" "}
          <Link to="/" className="link">
            home
          </Link>
          .
        </div>
      </div>
    </div>
  );
}

export default NotFound;
