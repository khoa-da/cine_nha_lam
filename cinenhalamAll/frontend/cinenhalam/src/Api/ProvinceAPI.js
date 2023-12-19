import axiosClient from "./AxiosClient";

const ProvinceAPI = {
  getAllProvincesByFilmAndDate(filmId, screeningDate) {
    const url = `http://localhost:8086/api/customer/province/${filmId}/date/${screeningDate}`;
    return axiosClient.get(url);
  },
};

export default ProvinceAPI;
