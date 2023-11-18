import React, { useState } from "react";
import { Autocomplete, AutocompleteItem, Button } from "@nextui-org/react";
import CountryType from "@/types/CountryType";
import GroupCountryType from "@/types/GroupCountryType";
import RegionWorldType from "@/types/RegionWorldType";

interface SelectWorldProps {
  countries: CountryType[];
  groupCountries: GroupCountryType[];
  regionWorld: RegionWorldType[];
}

const SelectWorld = ({
  countries,
  groupCountries,
  regionWorld,
}: SelectWorldProps) => {
  const [selectedTypeFilter, setSelectedTypeFilter] = useState<string>("");
  return (
    <>
      <div>
        <div className="text-center mt-3 font-bold text-lg">
          Choose the type of filter by world
        </div>
        <div className="flex flex-row gap-3 mt-3">
          <Button
            color="primary"
            onClick={() => setSelectedTypeFilter("country")}
          >
            Filter by Country
          </Button>
          <Button
            color="primary"
            onClick={() => setSelectedTypeFilter("groupCountry")}
          >
            Filter by Group of Countries
          </Button>
          <Button
            color="primary"
            onClick={() => setSelectedTypeFilter("region")}
          >
            Filter by Region
          </Button>
        </div>
        {selectedTypeFilter === "country" && (
          <div className="flex w-full flex-row justify-center md:flex-nowrap gap-4 mt-5">
            <Autocomplete
              label="Select a country"
              placeholder="Search an country"
              className="max-w-xs"
              defaultItems={countries}
              size="lg"
            >
              {(item) => (
                <AutocompleteItem key={item.countryCode}>
                  {item.countryName}
                </AutocompleteItem>
              )}
            </Autocomplete>
          </div>
        )}
        {selectedTypeFilter === "groupCountry" && (
          <div className="flex w-full flex-row justify-center md:flex-nowrap gap-4 mt-5">
            <Autocomplete
              label="Select a group of country"
              placeholder="Search an country"
              className="max-w-xs"
              defaultItems={groupCountries}
              size="lg"
            >
              {(item) => (
                <AutocompleteItem key={item.groupCountryCode}>
                  {item.groupCountryName}
                </AutocompleteItem>
              )}
            </Autocomplete>
          </div>
        )}
        {selectedTypeFilter === "region" && (
          <div className="flex w-full flex-row justify-center md:flex-nowrap gap-4 mt-5">
            <Autocomplete
              label="Select an region"
              placeholder="Search an region"
              className="max-w-xs"
              defaultItems={regionWorld}
              size="lg"
            >
              {(item) => (
                <AutocompleteItem key={item.regionName}>
                  {item.regionName}
                </AutocompleteItem>
              )}
            </Autocomplete>
          </div>
        )}
      </div>
    </>
  );
};

export default SelectWorld;
