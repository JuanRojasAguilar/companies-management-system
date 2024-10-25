'use client'

import CRUDPage from "@/components/root/crud";
import React from "react";

const ProductDetails = ({ params: { table } }) => {
  return (
	  <CRUDPage entity={table}/>
  );
};

export default ProductDetails;
