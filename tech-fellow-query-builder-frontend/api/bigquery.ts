import axios from "axios";

const URL_API = "http://localhost:8000/api/world";

const getCountries = () => {
  return axios.get(`${URL_API}/countries`);
};

const getGroupCountry = () => {
  return axios.get(`${URL_API}/groupCountries`);
};

const getRegionWorld = () => {
  return axios.get(`${URL_API}/regionsWorld`);
};

const bigQuery = { getCountries, getGroupCountry, getRegionWorld };

export default bigQuery;
