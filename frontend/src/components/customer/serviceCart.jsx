'use client'

import { useMemo } from 'react';
import { Card, CardBody, Button } from "@nextui-org/react";
import useLocalStorage from "@/lib/hooks/useLocalStorage";

const styles = {
  mainDiv: "w-full h-full flex flex-col justify-between",
  cardsDiv: "overflow-auto grow text-center",
  card: "h-12 mb-2",
  cardContent: "flex justify-between items-center",
  footerDiv: "flex flex-col items-center m-2",
};

const localStorageKey = "savedServices";

export default function ServiceCart() {
  const [selectedServices, setSelectedServices, clearServices] = useLocalStorage(localStorageKey, []);

  const removeService = (itemId) => {
    setSelectedServices(prevServices => prevServices.filter(item => item.id !== itemId));
  };

  const total = useMemo(() => {
    return selectedServices.reduce((acc, service) => acc + service.valueService, 0);
  }, [selectedServices]);

  const handlePurchase = () => {
    // Implementar lógica de compra aquí
    console.log("Compra realizada");
    setSelectedServices([]);
  };

  return (
    <div className={styles.mainDiv}>
      <div className={styles.cardsDiv}>
        {selectedServices.length > 0 ? (
          selectedServices.map((selectedService) => (
            <Card key={selectedService.id} className={styles.card}>
              <CardBody className={styles.cardContent}>
                <p>{selectedService.name}</p>
                <Button 
                  onClick={() => removeService(selectedService.id)}
                >
                  <i className="bx bx-trash-alt"></i>
                </Button>
              </CardBody>
            </Card>
          ))
        ) : (
          <h2 className="mt-4">No has seleccionado ningún servicio</h2>
        )}
      </div>
      <div className={styles.footerDiv}>
        <p>Total: ${total.toFixed(2)}</p>
        <Button 
          onClick={handlePurchase}
          isDisabled={selectedServices.length === 0}
        >
          <i className="bx bx-cart"></i> Comprar
        </Button>
      </div>
    </div>
  )
}
