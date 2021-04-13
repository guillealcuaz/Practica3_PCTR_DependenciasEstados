package src.p03.c01;

public class ActividadSalidaPuerta implements Runnable{


		private static final int NUMSALIDAS=20;
		private Parque parque;
		private String puerta;

		
		public ActividadSalidaPuerta(String puerta, IParque parque) {
			this.puerta=puerta;
			this.parque=parque;
		}
		

		@Override
		public void run() {
			for(int i=0;i<NUMSALIDAS;i++)
				parque.salirDelParque(puerta);
		}

	}

