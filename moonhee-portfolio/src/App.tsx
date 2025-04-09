import { useState } from 'react'
import './App.css'
import { QueryClient, QueryClientProvider } from '@tanstack/react-query'
import styled from 'styled-components';

const queryClient = new QueryClient(); 

const App : React.FC = () => {

  return (
    <QueryClientProvider client={queryClient}>
    </QueryClientProvider>
  )
}

export default App
