import axiosClient from "./AxiosClient";

const ProvinceAPI = {
  getAllProvincesByFilmAndDate(filmId, screeningDate) {
    const url = `/customer/province/${filmId}/date/${screeningDate}`;
    return axiosClient.get(url);
  },
};

export default ProvinceAPI;
