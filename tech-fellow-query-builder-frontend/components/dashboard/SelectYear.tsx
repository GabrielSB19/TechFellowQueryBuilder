import React, { useState, useEffect } from "react";

import { Slider, SliderValue } from "@nextui-org/react";

interface SelectYearProps {
  paramsYearMin: string;
  paramsYearMax: string;
  handleProperty: (property: string, value: string) => void;
}

const SelectYear = ({
  handleProperty,
  paramsYearMax,
  paramsYearMin,
}: SelectYearProps) => {
  const [value, setValue] = useState<SliderValue>([1999, 2005]);

  useEffect(() => {
    const range = value as number[];
    handleProperty("yearMin", range[0].toString());
    handleProperty("yearMax", range[1].toString());
  }, [value]);

  useEffect(() => {
    if (paramsYearMin && paramsYearMax)
      setValue([parseInt(paramsYearMin), parseInt(paramsYearMax)]);
  }, []);

  return (
    <div>
      <div className="text-center mt-3 font-bold text-lg">
        Choose the year range
      </div>
      <div>
        <Slider
          label="Year Range"
          step={1}
          minValue={1990}
          maxValue={2015}
          value={value}
          onChange={setValue}
          className="max-w-md"
        />
      </div>
    </div>
  );
};

export default SelectYear;
