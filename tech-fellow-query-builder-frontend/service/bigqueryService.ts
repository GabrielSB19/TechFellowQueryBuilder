import bigQuery from "@/api/bigquery";
import { toast } from "react-hot-toast";
import CountryType from "@/types/CountryType";
import GroupCountryType from "@/types/GroupCountryType";
import RegionWorldType from "@/types/RegionWorldType";

const fetchDataCountry = async () => {
  try {
    const response = await bigQuery.getCountries();
    const countries: CountryType[] = response.data;
    return countries;
  } catch (error) {
    toast.error("Error al cargar los países");
  }
};
const fetchDataGroupCountry = async () => {
  try {
    const response = await bigQuery.getGroupCountry();
    const groupCountries: GroupCountryType[] = response.data;
    return groupCountries;
  } catch (error) {
    toast.error("Error al cargar los grupos de países");
  }
};
const fetchDataRegionWorld = async () => {
  try {
    const response = await bigQuery.getRegionWorld();
    const regionsWorld: RegionWorldType[] = response.data;
    return regionsWorld;
  } catch (error) {
    toast.error("Error al cargar las regiones del mundo");
  }
};

const bigQueryServie = {
  fetchDataCountry,
  fetchDataGroupCountry,
  fetchDataRegionWorld,
};

export default bigQueryServie;
