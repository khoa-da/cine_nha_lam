import React from "react";
import { useRef, useState, useEffect } from "react";
import {
  faCheck,
  faTimes,
  faInfoCircle,
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

import { convertDate } from "../../Utils/date";

import "./Register.scss";

// import axios from "./api/axios";

const USER_REGEX = /^[A-z][A-z0-9-_]{3,23}$/;
const PWD_REGEX = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,24}$/;
const EMAIL_REGEX = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
const FULL_NAME_REGEX = /^[A-Za-z\s]+$/;
const DOB_REGEX = /^\d{4}-\d{2}-\d{2}$/;

const Register = () => {
  const userRef = useRef();
  const errRef = useRef();

  const [user, setUser] = useState("");
  const [validName, setValidName] = useState(false);
  const [userFocus, setUserFocus] = useState(false);

  const [pwd, setPwd] = useState("");
  const [validPwd, setValidPwd] = useState(false);
  const [pwdFocus, setPwdFocus] = useState(false);

  const [matchPwd, setMatchPwd] = useState("");
  const [validMatch, setValidMatch] = useState(false);
  const [matchFocus, setMatchFocus] = useState(false);

  const [email, setEmail] = useState("");
  const [validEmail, setValidEmail] = useState(false);
  const [emailFocus, setEmailFocus] = useState(false);

  const [fullName, setFullName] = useState("");
  const [validFullName, setValidFullName] = useState(false);
  const [fullNameFocus, setFullNameFocus] = useState(false);

  const [gender, setGender] = useState("");
  const [validGender, setValidGender] = useState(false);

  const [dob, setDob] = useState("");
  const [validDob, setValidDob] = useState(false);
  const [dobFocus, setDobFocus] = useState(false);

  useEffect(() => {
    setValidName(USER_REGEX.test(user));
  }, [user]);

  useEffect(() => {
    setValidPwd(PWD_REGEX.test(pwd));
    setValidMatch(pwd === matchPwd);
  }, [pwd, matchPwd]);

  useEffect(() => {
    setValidFullName(FULL_NAME_REGEX.test(fullName));
  }, [fullName]);

  useEffect(() => {
    setValidEmail(EMAIL_REGEX.test(email));
  }, [email]);

  // useEffect(() => {
  //   // setValidDob(DOB_REGEX.test(dob));
  //   const inputDate = new Date(dob);
  //   const currentDate = new Date();

  //   const isDobValid = inputDate <= currentDate;

  //   setValidDob(isDobValid);
  // }, [dob]);

  const handleDobChange = (event) => {
    const selectedDate = event.target.value;

    // Kiểm tra ngày sinh có lớn hơn ngày hiện tại không
    const inputDate = new Date(selectedDate);
    const currentDate = new Date();
    const isDobValid = inputDate <= currentDate;
    console.log(inputDate.toISOString());
    console.log(isDobValid);
    console.log(currentDate.toISOString().slice(0, 10));
    if (isDobValid) {
      setDob(selectedDate);
    } else {
      setDob(convertDate(currentDate));
      console.log("Invalid date of birth");
    }
  };

  const handleSubmit = async (e) => {};

  return (
    <div className="register-container">
      <div className="register-inner">
        <div className="username">
          <label htmlFor="username">Username:</label>
          <input
            type="text"
            id="username"
            // ref={userRef}
            autoComplete="off" //cái này dùng để k tự độn gợi ý
            onChange={(e) => setUser(e.target.value)}
            value={user}
            required
            aria-invalid={validName ? "false" : "true"} //có tác dụn khi ng khiếm thị nhập sai, dev phải tuân thủ theo quy tắc này
            aria-describedby="uidnote"
            onFocus={() => setUserFocus(true)}
            onBlur={() => setUserFocus(false)}
            className="input-username"
            placeholder="User name"
          />
          {validName ? <span>đúng</span> : <span>sai</span>}
          <p
            id="uidnote"
            className={userFocus && !validName ? "instructions" : "offscreen"}
          >
            4 to 24 characters.
            <br />
            Must begin with a letter.
            <br />
            Letters, numbers, underscores, hyphens allowed.
          </p>
          {/* {console.log(userRef.current.value)} userRef dùng để lấy giá trị */}
        </div>

        <div className="pwd">
          <label htmlFor="pwd">Password:</label>
          <input
            id="pwd"
            // ref={userRef}
            value={pwd}
            onChange={(e) => setPwd(e.target.value)}
            autoComplete="off"
            required
            ariaInvalid={validPwd ? "false" : "true"} //có tác dụn khi ng khiếm thị nhập sai, dev phải tuân thủ theo quy tắc này
            ariaDescribedby="pwdnote"
            onFocus={() => setPwdFocus(true)}
            onBlur={() => setPwdFocus(false)}
            type="password"
            className="input-password"
            placeholder="Password"
          />

          {validPwd ? <span>đúng</span> : <span>sai</span>}
          {/* {console.log(userRef.current.value)} */}
          <p
            id="pwdnote"
            className={pwdFocus && !validPwd ? "instructions" : "offscreen"}
          >
            <FontAwesomeIcon icon={faInfoCircle} />
            8 to 24 characters.
            <br />
            Must include uppercase and lowercase letters, a number and a special
            character.
            <br />
            Allowed special characters:{" "}
            <span aria-label="exclamation mark">!</span>{" "}
            <span aria-label="at symbol">@</span>{" "}
            <span aria-label="hashtag">#</span>{" "}
            <span aria-label="dollar sign">$</span>{" "}
            <span aria-label="percent">%</span>
          </p>
        </div>

        <div className="fullname">
          <label htmlFor="full-name">Full Name</label>
          <input
            id="full-name"
            type="text"
            required
            value={fullName}
            autoComplete="off"
            onChange={(e) => setFullName(e.target.value)}
            ariaInvalid={validFullName ? "false" : "true"} //có tác dụn khi ng khiếm thị nhập sai, dev phải tuân thủ theo quy tắc này
            ariaDescribedby="fullnameNote"
            onFocus={() => setFullNameFocus(true)}
            onBlur={() => setFullNameFocus(false)}
            className="input-fullname"
            placeholder="Full name"
          />

          {validFullName ? <span>đúng</span> : <span>sai</span>}

          <p
            id="fullnameNote"
            className={
              fullNameFocus && !validFullName ? "instructions" : "offscreen"
            }
          >
            <FontAwesomeIcon icon={faInfoCircle} />
            Full name cannot contain numbers or special characters:
            <br /> <span aria-label="exclamation mark">!</span>{" "}
            <span aria-label="at symbol">@</span>{" "}
            <span aria-label="hashtag">#</span>{" "}
            <span aria-label="dollar sign">$</span>{" "}
            <span aria-label="percent">%</span>
          </p>
        </div>

        <div className="email">
          <label htmlFor="email">Email</label>
          <input
            id="email"
            type="email"
            value={email}
            required
            autoComplete="off"
            onChange={(e) => setEmail(e.target.value)}
            ariaInvalid={validEmail ? "false" : "true"} //có tác dụn khi ng khiếm thị nhập sai, dev phải tuân thủ theo quy tắc này
            ariaDescribedby="emailNote"
            onFocus={() => setEmailFocus(true)}
            onBlur={() => setEmailFocus(false)}
            className="input-email"
            placeholder="Email"
          />
          {validEmail ? <span>đúng</span> : <span>sai</span>}
          <p
            id="emailNote"
            className={emailFocus && !validEmail ? "instructions" : "offscreen"}
          >
            <FontAwesomeIcon icon={faInfoCircle} />
            Example: join@example.com
          </p>
        </div>

        <div className="dob">
          <label htmlFor="birthday">Birthday</label>

          <input
            id="birthday"
            value={dob}
            onChange={handleDobChange}
            ariaInvalid={validDob ? "false" : "true"} //có tác dụn khi ng khiếm thị nhập sai, dev phải tuân thủ theo quy tắc này
            ariaDescribedby="dobNote"
            onFocus={() => setDobFocus(true)}
            onBlur={() => setDobFocus(false)}
            type="date"
            className="dob"
            placeholder="Birthday"
          />

          {validDob ? <span>đúng</span> : <span>sai</span>}
        </div>

        <div className="gender">
          <label for="gender"> Gender</label>
          <select id="gender" name="gender">
            <option value="male">Male</option>
            <option value="female">Female</option>
          </select>
        </div>

        <div className="btn-register">
          <button type="submit">Register</button>
        </div>
      </div>
    </div>
  );
};
export default Register;
