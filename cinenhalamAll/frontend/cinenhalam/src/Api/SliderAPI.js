import axiosClient from "./AxiosClient";

const SliderAPI = {
  getAll() {
    const url = "/admin/slider";
    console.log(url);
    return axiosClient.get(url);
  },
};
export default SliderAPI;
