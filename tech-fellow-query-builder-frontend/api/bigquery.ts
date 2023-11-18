import axios from "axios";

const getCountries = () => {
  return axios.get("http://localhost:8000/api/world/countries");
};

const getGroupCountry = () => {
  return axios.get("http://localhost:8000/api/world/groupCountries");
};

const getRegionWorld = () => {
  return axios.get("http://localhost:8000/api/world/regionsWorld");
};

const bigQuery = { getCountries, getGroupCountry, getRegionWorld };

export default bigQuery;
