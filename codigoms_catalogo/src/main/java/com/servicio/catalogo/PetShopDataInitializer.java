package com.servicio.catalogo;

import com.servicio.catalogo.model.Producto;
import com.servicio.catalogo.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PetShopDataInitializer implements CommandLineRunner {

    private final ProductoRepository repository;

    @Override
    public void run(String... args) {
        if (repository.count() > 0) return;

        List<Producto> productos = List.of(
                producto("Alimento para perros", "Alimento nutritivo para perros adultos.", 12990, "perros", "alimentos"),
                producto("Alimento para gatos", "Alimento completo y equilibrado para gatos.", 10990, "gatos", "alimentos"),
                producto("Juguete para perros", "Juguete resistente para entretener a tu mascota.", 7990, "perros", "juguetes"),
                producto("Juguete para gatos", "Juguete interactivo para gatos.", 5990, "gatos", "juguetes"),
                producto("Collar para perro", "Collar cómodo y ajustable.", 6990, "perros", "accesorios"),
                producto("Correa para perro", "Correa resistente para paseos.", 8990, "perros", "accesorios"),
                producto("Cama para mascotas", "Cama cómoda para el descanso de tu mascota.", 19990, "perros gatos", "camas"),
                producto("Plato para mascotas", "Plato práctico para comida y agua.", 4990, "perros gatos", "accesorios"),
                producto("Snack para perros", "Premios ideales para consentir a tu mascota.", 3990, "perros", "snacks"),
                producto("Snack para gatos", "Deliciosos premios para gatos.", 3490, "gatos", "snacks"),
                producto("Shampoo para mascotas", "Shampoo suave para el cuidado del pelaje.", 7490, "perros gatos", "higiene"),
                producto("Cepillo para mascotas", "Ayuda a mantener el pelaje limpio y saludable.", 5490, "perros gatos", "higiene")
        );

        repository.saveAll(productos);
    }

    private Producto producto(String nombre, String descripcion, int precio, String animal, String categoria) {
        Producto p = new Producto();
        p.setNombre(nombre);
        p.setDescripcion(descripcion);
        p.setPrecio(BigDecimal.valueOf(precio));
        p.setImagen("img/prueba1.png");
        p.setAnimal(animal);
        p.setCategoria(categoria);
        p.setStock(100);
        p.setDisponible(true);
        return p;
    }
}
