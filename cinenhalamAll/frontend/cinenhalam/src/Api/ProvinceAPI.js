import axiosClient from "./AxiosClient";

const ProvinceAPI = {
  getAllProvincesByFilmAndDate(filmId, screeningDate) {
    const url = `customer/province/${filmId}/date/${screeningDate}`;
    console.log(url)
    return axiosClient.get(url);
  },
};

export default ProvinceAPI;
