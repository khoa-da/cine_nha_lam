import axiosClient from "./AxiosClient";

const CinemaAPI = {
  getCinemaThatShowThisFilm(filmId, selectedProvince, selectedDate) {
    const url = `/customer/cinema/film/${filmId}/province/${selectedProvince}/date/${selectedDate}`;
    return axiosClient.get(url);
  },

};

export default CinemaAPI;
