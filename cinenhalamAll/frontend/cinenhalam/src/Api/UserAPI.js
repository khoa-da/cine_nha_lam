import axiosClient from "./AxiosClient";

const userAPI = {
  register(data) {
    const url = "/admin/user";
    return axiosClient.post(url);
  },
};
export default userAPI;
