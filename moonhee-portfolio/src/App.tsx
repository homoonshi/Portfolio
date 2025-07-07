import './App.css'
import { QueryClient, QueryClientProvider } from '@tanstack/react-query'
import { Outlet } from 'react-router-dom';
import { Header } from './components/Header';

const queryClient = new QueryClient(); 

const App : React.FC = () => {

  return (
    <QueryClientProvider client={queryClient}>
      <div className='font-sans'>
        <Header/>
        <div className='w-full h-full'>
          <Outlet/>
        </div>
        <div className='w-full h-auto'></div>
      </div>
    </QueryClientProvider>
  )
}

export default App
