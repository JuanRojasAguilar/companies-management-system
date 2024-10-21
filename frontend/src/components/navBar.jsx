'use client'

import { useState, useEffect } from 'react'
import { usePathname, useRouter } from 'next/navigation'
import { Button } from "@nextui-org/react"

export default function NavigationBar({ pages }) {
  const router = useRouter()
  const pathname = usePathname()
  const [currentPage, setCurrentPage] = useState(1)

  useEffect(() => {
    const index = pages.indexOf(pathname)
    if (index !== -1) {
      setCurrentPage(index + 1)
    }
  }, [pathname, pages])

  const navigateTo = (direction) => {
    let newPage = direction === 'up' ? currentPage - 1 : currentPage + 1

    if (newPage < 1) newPage = pages.length
    if (newPage > pages.length) newPage = 1

    router.push(pages[newPage - 1])
    setCurrentPage(newPage)
  }

  const goToPage = (index) => {
    router.push(pages[index])
    setCurrentPage(index + 1)   }

  return (
    <nav className="fixed right-4 top-1/2 transform -translate-y-1/2 flex flex-col items-center space-y-4">
      {/* Navigate up */}
      <Button
        isIconOnly
        aria-label="Navigate up"
        variant="light"
        onPress={() => navigateTo('up')}
      >
        <i className="bx bx-chevron-up"></i>
      </Button>

      {/* Page numbers */}
      <div className="flex flex-col items-center space-y-2">
        {pages.map((_, index) => (
          <Button
            key={index}
            size="sm"
            isIconOnly
            variant={currentPage === index + 1 ? "solid" : "light"} 
            onPress={() => goToPage(index)}
            aria-label={`Go to page ${index + 1}`}
            className="w-8 h-8"
          >
            {index + 1}
          </Button>
        ))}
      </div>

      {/* Navigate down */}
      <Button
        isIconOnly
        aria-label="Navigate down"
        variant="light"
        onPress={() => navigateTo('down')}
      >
        <i className="bx bx-chevron-down"></i>
      </Button>
    </nav>
  )
}
