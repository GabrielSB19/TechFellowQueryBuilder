import React, { useState, useEffect } from "react";
import { Slider, SliderValue } from "@nextui-org/react";

interface SelectAgeProps {
  handleProperty: (property: string, value: string) => void;
}

const SelectAge = ({ handleProperty }: SelectAgeProps) => {
  const [value, setValue] = useState<SliderValue>([9, 15]);

  useEffect(() => {
    const range = value as number[];
    handleProperty("ageMin", range[0].toString());
    handleProperty("ageMax", range[1].toString());
  }, [value]);

  return (
    <div>
      <div className="text-center mt-3 font-bold text-lg">
        Choose the age range
      </div>
      <div>
        <Slider
          label="Age Range"
          step={1}
          minValue={0}
          maxValue={25}
          value={value}
          onChange={setValue}
          defaultValue={[9, 15]}
          className="max-w-md"
        />
      </div>
    </div>
  );
};

export default SelectAge;
