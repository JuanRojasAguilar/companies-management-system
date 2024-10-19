'use cli    ent'

import { useState, useEffect } from 'react'
import { usePathname, useRouter } from 'next/navigation'
import { Button } from "@nextui-org/react"

const pages = [
  '/customer/service-purchase',
]

export default function CustomerNavigation() {
  const router = useRouter()
  const pathname = usePathname()
  const [currentPage, setCurrentPage] = useState(1)

  useEffect(() => {
    const index = pages.indexOf(pathname)
    if (index !== -1) {
      setCurrentPage(index + 1)
    }
  }, [pathname])

  const navigateTo = (direction) => {
    let newPage = direction === 'up' ? currentPage - 1 : currentPage + 1
    if (newPage < 1) newPage = pages.length
    if (newPage > pages.length) newPage = 1
    router.push(pages[newPage - 1])
  }

  return (
    <nav className="fixed right-4 top-1/2 transform -translate-y-1/2 flex flex-col items-center space-y-4">
      <Button
        isIconOnly
        aria-label="Navigate up"
        variant="light"
        onPress={() => navigateTo('up')}
      >
        <i class='bx bx-chevron-up' ></i>
      </Button>
      <div className="flex flex-col items-center space-y-2">
        {pages.map((_, index) => (
          <Button
            key={index}
            size="sm"
            isIconOnly
            variant={currentPage === index + 1 ? "solid" : "light"}
            onPress={() => router.push(pages[index])}
            aria-label={`Go to page ${index + 1}`}
            className="w-8 h-8"
          >
            {index + 1}
          </Button>
        ))}
      </div>
      <Button
        isIconOnly
        aria-label="Navigate down"
        variant="light"
        onPress={() => navigateTo('down')}
      >
        <i class='bx bx-chevron-down' ></i>
      </Button>
    </nav>
  )
}