import { useState, useEffect } from 'react';
import { Table, TableRow, TableBody, TableCell, TableColumn, TableHeader, Button } from '@nextui-org/react';
import axios from 'axios';

export default function ProductList() {
  const [products, setProducts] = useState([]);
  const [stocks, setStocks] = useState([]);

  useEffect(() => {
    // Fetch product data
    axios.get('/api/products') // Replace with your actual API endpoint
      .then(response => setProducts(response.data))
      .catch(error => console.error('Error fetching products:', error));

    // Fetch stock data
    axios.get('/api/stocks') // Replace with your actual API endpoint
      .then(response => setStocks(response.data))
      .catch(error => console.error('Error fetching stocks:', error));
  }, []);

  const checkConditions = (stock, requiredProducts, minStock) => {
    const isStockEnough = stock >= requiredProducts;
    const isAboveMinStock = (stock - requiredProducts) >= minStock;
    return { isStockEnough, isAboveMinStock };
  };

  return (
    <div className="space-y-4 h-full w-full p-8 pr-16">
	  <h1 className="text-lg text-center pb-4">Products List</h1>
      <Table aria-label="Product Table" css={{ marginTop: '20px' }}>
        <TableHeader>
          <TableColumn>Product</TableColumn>
          <TableColumn>Required Products</TableColumn>
          <TableColumn>Stock</TableColumn>
          <TableColumn>Min Stock</TableColumn>
          <TableColumn>Approval</TableColumn>
        </TableHeader>
        <TableBody>
          {products.map((product, index) => {
            const stockData = stocks.find(stock => stock.productId === product.id);
            const stock = stockData ? stockData.stock : 0;
            const requiredProducts = product.requiredProducts;
            const minStock = product.minStock;

            const { isStockEnough, isAboveMinStock } = checkConditions(stock, requiredProducts, minStock);

            return (
              <TableRow key={index}>
                <TableCell>{product.name}</TableCell>
                <TableCell>{requiredProducts}</TableCell>
                
                {/* Stock Cell with Conditional Color */}
                <TableCell css={{ color: isStockEnough ? 'black' : 'red' }}>
                  {stock}
                </TableCell>
                
                {/* Min Stock Cell with Conditional Color */}
                <TableCell css={{ color: isAboveMinStock ? 'black' : 'orange' }}>
                  {minStock}
                </TableCell>
                
                {/* Approval Button */}
                <TableCell>
                  <Button 
                    color="success" 
                    disabled={!(isStockEnough && isAboveMinStock)}
                  >
                    Approve
                  </Button>
                </TableCell>
              </TableRow>
            );
          })}
        </TableBody>
      </Table>
    </div>
  );
}
