import React from "react";
import { useRef, useState, useEffect } from "react";
import {
  faCheck,
  faTimes,
  faInfoCircle,
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

import RegisterAPI from "../../Api/RegisterAPI";

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

  const [status, setStatus] = useState(false);
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

  const handleSubmit = async (e) => {
    try {
      const config = {
        headers: {
          "Content-type": "application/json",
        },
      };
      setStatus(true);
      const dataForUser = {
        userName: user,
        email,
        password: pwd,
        fullName,
        dob,
        gender,
        status: true,
        roleId: [1],
      };

      console.log(gender);
      const register = await RegisterAPI.register(dataForUser, config);
      console.log(register);

      // toast({
      //   title: "Register successfully",
      //   status: "success",
      //   duration: 5000,
      //   isClosable: true,
      //   position: "bottom",
      // });

      localStorage.setItem("userInfo", JSON.stringify(dataForUser));
      // setLoading(false);
    } catch (error) {
      // toast({
      //   title: "Error occur!",
      //   // description: error.register.message,
      //   status: "error",
      //   duration: 5000,
      //   isClosable: true,
      //   position: "bottom",
      // });
      console.log("cccccc");
    }
  };

  return (
    <div className="register-container">
      <div className="register-inner">
        <div className="username">
          <label htmlFor="username">Username:</label>
          <FontAwesomeIcon
            icon={faCheck}
            className={validName ? "valid" : "hide"}
          />
          <FontAwesomeIcon
            icon={faTimes}
            className={validName || !user ? "hide" : "invalid"}
          />

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
            className="register-input"
            placeholder="User name"
          />

          <p
            id="uidnote"
            className={userFocus && !validName ? "instructions" : "offscreen"}
          >
            Username must be 4 to 24 characters long, start with a letter, and
            can include letters, numbers, underscores, and hyphens.
          </p>
          {/* {console.log(userRef.current.value)} userRef dùng để lấy giá trị */}
        </div>

        <div className="pwd">
          <label htmlFor="pwd">Password:</label>
          <FontAwesomeIcon
            icon={faCheck}
            className={validPwd ? "valid" : "hide"}
          />
          <FontAwesomeIcon
            icon={faTimes}
            className={validPwd || !pwd ? "hide" : "invalid"}
          />
          <input
            id="pwd"
            // ref={userRef}
            value={pwd}
            onChange={(e) => setPwd(e.target.value)}
            autoComplete="off"
            required
            aria-invalid={validPwd ? "false" : "true"} //có tác dụn khi ng khiếm thị nhập sai, dev phải tuân thủ theo quy tắc này
            aria-describedby="pwdnote"
            onFocus={() => setPwdFocus(true)}
            onBlur={() => setPwdFocus(false)}
            type="password"
            className="register-input"
            placeholder="Password"
          />

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

        <div className="confirm_pwd">
          <label htmlFor="confirm_pwd">Confirm Password:</label>
          <FontAwesomeIcon
            icon={faCheck}
            className={validMatch && matchPwd ? "valid" : "hide"}
          />
          <FontAwesomeIcon
            icon={faTimes}
            className={validMatch || !matchPwd ? "hide" : "invalid"}
          />
          <input
            type="password"
            id="confirm_pwd"
            onChange={(e) => setMatchPwd(e.target.value)}
            value={matchPwd}
            required
            aria-invalid={validMatch ? "false" : "true"}
            aria-describedby="confirmnote"
            onFocus={() => setMatchFocus(true)}
            onBlur={() => setMatchFocus(false)}
            placeholder="Confirm Password"
            className="register-input"
          />
          <p
            id="confirmnote"
            className={matchFocus && !validMatch ? "instructions" : "offscreen"}
          >
            <FontAwesomeIcon icon={faInfoCircle} />
            Must match the first password input field.
          </p>
        </div>

        <div className="fullname">
          <label htmlFor="full-name">Full Name</label>
          <FontAwesomeIcon
            icon={faCheck}
            className={validFullName ? "valid" : "hide"}
          />
          <FontAwesomeIcon
            icon={faTimes}
            className={validFullName || !fullName ? "hide" : "invalid"}
          />
          <input
            id="full-name"
            type="text"
            required
            value={fullName}
            autoComplete="off"
            onChange={(e) => setFullName(e.target.value)}
            aria-invalid={validFullName ? "false" : "true"} //có tác dụn khi ng khiếm thị nhập sai, dev phải tuân thủ theo quy tắc này
            aria-describedby="fullnameNote"
            onFocus={() => setFullNameFocus(true)}
            onBlur={() => setFullNameFocus(false)}
            className="register-input"
            placeholder="Full name"
          />

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
          <FontAwesomeIcon
            icon={faCheck}
            className={validEmail ? "valid" : "hide"}
          />
          <FontAwesomeIcon
            icon={faTimes}
            className={validEmail || !email ? "hide" : "invalid"}
          />
          <input
            id="email"
            type="email"
            value={email}
            required
            autoComplete="off"
            onChange={(e) => setEmail(e.target.value)}
            aria-invalid={validEmail ? "false" : "true"} //có tác dụn khi ng khiếm thị nhập sai, dev phải tuân thủ theo quy tắc này
            aria-describedby="emailNote"
            onFocus={() => setEmailFocus(true)}
            onBlur={() => setEmailFocus(false)}
            className="register-input"
            placeholder="Email"
          />

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
          <FontAwesomeIcon
            icon={faCheck}
            className={validDob ? "valid" : "hide"}
          />
          <FontAwesomeIcon
            icon={faTimes}
            className={validDob || !dob ? "hide" : "invalid"}
          />
          <input
            id="birthday"
            value={dob}
            onChange={handleDobChange}
            aria-invalid={validDob ? "false" : "true"} //có tác dụn khi ng khiếm thị nhập sai, dev phải tuân thủ theo quy tắc này
            aria-describedby="dobNote"
            onFocus={() => setDobFocus(true)}
            onBlur={() => setDobFocus(false)}
            type="date"
            className="register-input"
            placeholder="Birthday"
          />
        </div>

        <div className="gender">
          <label htmlFor="gender"> Gender</label>
          <select
            id="gender"
            name="gender"
            required
            value={gender}
            onChange={(e) => setGender(e.target.value)}
          >
            <option value={true}>Male</option>
            <option value={false}>Female</option>
          </select>
        </div>

        <div className="btn-register">
          <button onClick={handleSubmit}>Register</button>
        </div>
      </div>
    </div>
  );
};
export default Register;
