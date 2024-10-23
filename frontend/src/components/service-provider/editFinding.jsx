"use client";

import { useState } from "react";
import { Card, Textarea, Button, Input, Spacer, CardHeader, CardBody, CardFooter } from "@nextui-org/react";
import useLocalStorage from "@/lib/hooks/useLocalStorage";

export default function ServiceFindingCard() {
  const [localStorageService, setLocalStorageService] = useLocalStorage(
    "serviceFinding",
    {}
  );
  const [finding, setFinding] = useState(localStorageService.finding || "");

  const handleConfirmEdit = async () => {
    try {
      const response = await fetch(`/api/services/${localStorageService.id}`, {
        method: "PATCH",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ finding }),
      });

      if (response.ok) {
        console.log("Service finding updated successfully!");
      }
    } catch (error) {
      console.error("Error updating service finding:", error);
    }
  };

  return (
	  <div className="p-8 flex h-full w-full justify-center items-center">
	 	 <Card className="w-11/12 h-full">
	 	 	<CardHeader className="px-8 pt-6 text-lg">
	  			titulo hardcodeado
			</CardHeader>
			<CardBody className="h-full">
	  			<Textarea
	  				type="text"
	  				defaultValue="Texto muy hardcodeado"
	  				disableAutosize
	  				// value={finding}
	  				//onChange={(e) => setFinding(e.target.value)}
	  				onValueChange={setFinding}
	  				label="Service Finding"
					classNames={{
        				base: "max-w-4xl p-4",
        				input: "resize-y min-h-[300px]",
      				}}				
	  			/>
			</CardBody>
	  		<CardFooter>
	  			<Button color="success" variant="ghost" onClick={handleConfirmEdit}>
       			   Confirm Edits
       			 </Button>
       			 <Spacer x={0.5} />
       			 <Button color="warning" variant="ghost" onClick={() => setFinding(localStorageService.finding)}>
       			   Cancel
       			 </Button>
	  		</CardFooter>
  	 	 </Card>
	  </div>
  );
}
