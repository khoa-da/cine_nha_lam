import axiosClient from "./AxiosClient";

const FilmAPI = {
  getAllShowing() {
    const url = "/customer/filmShowing";
    console.log(url);
    return axiosClient.get(url);
  },

  getAllComing() {
    const url = "/customer/filmComing";
    return axiosClient.get(url);
  },

  getFilmDetail(filmId) {
    const url = `/customer/film/${filmId}`;
    console.log(url);
    return axiosClient.get(url);
  }
};

export default FilmAPI;
