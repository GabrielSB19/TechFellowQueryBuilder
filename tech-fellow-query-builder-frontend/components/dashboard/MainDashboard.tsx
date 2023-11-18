"use client";

import CountryType from "@/types/CountryType";
import GroupCountryType from "@/types/GroupCountryType";
import RegionWorldType from "@/types/RegionWorldType";
import React from "react";
import SelectWorld from "./SelectWorld";
import SelectAge from "./SelectAge";
import SelectYear from "./SelectYear";
import SelectGender from "./SelectGender";

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
  return (
    <>
      <div>
        <div className="text-center text-xl mt-4">
          Below you can choose the filters you want to see the behavior of the
          population in the world.
        </div>
        <div className="flex flex-row justify-around align-middle">
          <SelectWorld
            countries={countries}
            groupCountries={groupCountries}
            regionWorld={regionWorld}
          />
          <SelectAge />
          <SelectYear />
          <SelectGender />
        </div>
      </div>
    </>
  );
};

export default MainDashboard;
