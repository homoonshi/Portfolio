import './App.css'
import { QueryClient, QueryClientProvider } from '@tanstack/react-query'
import { Outlet } from 'react-router-dom';

const queryClient = new QueryClient(); 

const App : React.FC = () => {

  return (
    <QueryClientProvider client={queryClient}>
      <div className='w-full h-auto'></div>
      <Outlet/>
      <div className='w-full h-auto'></div>
    </QueryClientProvider>
  )
}

export default App
