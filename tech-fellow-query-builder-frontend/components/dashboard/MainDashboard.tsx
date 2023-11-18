import CountryType from "@/types/CountryType";
import GroupCountryType from "@/types/GroupCountryType";
import RegionWorldType from "@/types/RegionWorldType";
import React from "react";
import NavbarComponent from "../navbar/NavbarComponent";

interface MainDashboardProps {
  countries: CountryType[];
  groupCountries: GroupCountryType[];
  regionWorld: RegionWorldType[];
}

const MainDashboard = ({
  countries,
  groupCountries,
  regionWorld,
}: MainDashboardProps) => {
  return <p>Perra</p>;
};

export default MainDashboard;
