import React from "react";
import { Slider } from "@nextui-org/react";

const SelectAge = () => {
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
          defaultValue={[3, 22]}
          className="max-w-md"
        />
      </div>
    </div>
  );
};

export default SelectAge;
