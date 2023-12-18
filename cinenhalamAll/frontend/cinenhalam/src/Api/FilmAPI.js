import axiosClient from "./AxiosClient";

const FilmAPI = {
  getAllShowing() {
    const url = "/customer/filmShowing";
    return axiosClient.get(url);
  },

  getAllComing() {
    const url = "/customer/filmComing";
    return axiosClient.get(url);
  },
};

export default FilmAPI;
