import React, { useState, useEffect } from "react";

import { Slider, SliderValue } from "@nextui-org/react";

interface SelectYearProps {
  handleProperty: (property: string, value: string) => void;
}

const SelectYear = ({ handleProperty }: SelectYearProps) => {
  const [value, setValue] = useState<SliderValue>([1999, 2005]);

  useEffect(() => {
    const range = value as number[];
    handleProperty("yearMin", range[0].toString());
    handleProperty("yearMax", range[1].toString());
  }, [value]);

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
          defaultValue={[1991, 2005]}
          className="max-w-md"
        />
      </div>
    </div>
  );
};

export default SelectYear;
