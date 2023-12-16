import axiosClient from "./AxiosClient";

const SliderAPI = {
  getAll() {
    const url = "/admin/slider";
    return axiosClient.get(url);
  },
};
export default SliderAPI;
