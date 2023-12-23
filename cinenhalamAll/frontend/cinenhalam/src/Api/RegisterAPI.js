import axiosClient from "./AxiosClient";

const RegisterAPI = {
  register(data, config) {
    const url = "/admin/user";
    return axiosClient.post(url, data, config);
  },
};
export default RegisterAPI;
