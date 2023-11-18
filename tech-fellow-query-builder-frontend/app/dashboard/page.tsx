"use client";

import React, { useEffect, useState } from "react";

import bigQueryServie from "@/service/bigqueryService";

import { toast } from "react-hot-toast";
import { CircularProgress } from "@nextui-org/progress";
import CountryType from "@/types/CountryType";
import GroupCountryType from "@/types/GroupCountryType";
import RegionWorldType from "@/types/RegionWorldType";
import MainDashboard from "@/components/dashboard/MainDashboard";
import NavbarComponent from "@/components/navbar/NavbarComponent";

const DashboardPage = () => {
  const [countries, setCountries] = useState<CountryType[]>([]);
  const [groupedCountries, setGroupedCountries] = useState<GroupCountryType[]>(
    []
  );
  const [regions, setRegions] = useState<RegionWorldType[]>([]);

  const fetchData = async () => {
    try {
      const [dataCountry, dataGroupedCountry, dataRegion] = await Promise.all([
        bigQueryServie.fetchDataCountry(),
        bigQueryServie.fetchDataGroupCountry(),
        bigQueryServie.fetchDataRegionWorld(),
      ]);

      if (dataCountry) setCountries(dataCountry);
      if (dataGroupedCountry) setGroupedCountries(dataGroupedCountry);
      if (dataRegion) setRegions(dataRegion);
    } catch (error) {
      toast.error("Error al cargar datos");
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  if (!countries.length || !groupedCountries.length || !regions.length)
    return (
      <>
        <NavbarComponent selected="Home" />
        <div className="flex items-center justify-center h-[100%]">
          <CircularProgress label="Loading" size="lg" />
        </div>
      </>
    );

  return (
    <>
      <NavbarComponent selected="Home" />
      <MainDashboard
        countries={countries}
        groupCountries={groupedCountries}
        regionWorld={regions}
      />
    </>
  );
};

export default DashboardPage;
