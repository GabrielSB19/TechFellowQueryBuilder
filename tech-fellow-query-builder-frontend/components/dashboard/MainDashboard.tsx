"use client";

import CountryType from "@/types/CountryType";
import GroupCountryType from "@/types/GroupCountryType";
import RegionWorldType from "@/types/RegionWorldType";
import React, { useEffect, useState } from "react";
import SelectWorld from "./SelectWorld";
import SelectAge from "./SelectAge";
import SelectYear from "./SelectYear";
import SelectGender from "./SelectGender";
import { Button } from "@nextui-org/button";
import GetDataType from "@/types/GetDataType";
import getDataService from "@/service/getDataService";
import { toast } from "react-hot-toast";
import DataGraphType from "@/types/DataGraphType";
import Graph from "../graph/Graph";
import { CircularProgress, Modal } from "@nextui-org/react";
import ModalSaveQuery from "../saveQuery/ModalSaveQuery";

interface MainDashboardProps {
  countries: CountryType[];
  groupCountries: GroupCountryType[];
  regionWorld: RegionWorldType[];
}

const queryDataForm: GetDataType = {
  codeCountry: "",
  codeRegion: "",
  gender: "",
  ageMin: "",
  ageMax: "",
  yearMin: "",
  yearMax: "",
};

const MainDashboard = ({
  countries,
  groupCountries,
  regionWorld,
}: MainDashboardProps) => {
  const [isEnable, setIsEnable] = useState<boolean>(false);
  const [queryData, setQueryData] = useState<GetDataType>(queryDataForm);
  const [isQueryLoad, setIsQuery] = useState<boolean>(false);
  const [dataGraph, setDataGraph] = useState<DataGraphType>();

  useEffect(() => {
    const areAllFieldsComplete =
      (!!queryData.codeCountry || !!queryData.codeRegion) &&
      !!queryData.gender &&
      !!queryData.ageMin &&
      !!queryData.ageMax &&
      !!queryData.yearMin &&
      !!queryData.yearMax;
    setIsEnable(areAllFieldsComplete);
  }, [queryData]);

  const handleProperty = (property: string, value: string) => {
    setQueryData((prevQueryData) => {
      let updatedQueryData = { ...prevQueryData, [property]: value };

      if (property === "codeCountry") {
        updatedQueryData = { ...updatedQueryData, ["codeRegion"]: "" };
      }

      if (property === "codeRegion") {
        updatedQueryData = { ...updatedQueryData, ["codeCountry"]: "" };
      }

      console.log(property, value);
      console.log(updatedQueryData);

      return updatedQueryData;
    });
  };

  const doQuery = async () => {
    try {
      setIsQuery(true);
      const dataGraph = await getDataService.doQuery(queryData);
      setDataGraph(dataGraph);
      setIsQuery(false);
    } catch (error) {
      toast.error("Error in the query");
    }
  };

  return (
    <>
      <div>
        <div className="text-center text-xl mt-4">
          Below you can choose the filters you want to see the behavior of the
          population in the world.
        </div>
        <div className="flex flex-row justify-between">
          <SelectWorld
            countries={countries}
            groupCountries={groupCountries}
            regionWorld={regionWorld}
            handleProperty={handleProperty}
          />
          <div className="flex flex-col w-[30%]">
            <SelectAge handleProperty={handleProperty} />
            <SelectYear handleProperty={handleProperty} />
          </div>
          <SelectGender handleProperty={handleProperty} />
        </div>
        <div className="mt-10 flex flex-row justify-center w-[100%]">
          <Button
            color="primary"
            className="w-[30%]"
            isDisabled={!isEnable}
            onClick={doQuery}
          >
            Run Query
          </Button>
        </div>
        {isQueryLoad ? (
          <div className="flex items-center justify-center h-[100%] mt-20">
            <CircularProgress label="Loading" size="lg" />
          </div>
        ) : (
          dataGraph && (
            <div>
              <Graph queryData={dataGraph} />
              <div className="flex flex-row justify-center mb-10 w-[100%]">
                <ModalSaveQuery queryData={dataGraph} />
              </div>
            </div>
          )
        )}
      </div>
    </>
  );
};

export default MainDashboard;
