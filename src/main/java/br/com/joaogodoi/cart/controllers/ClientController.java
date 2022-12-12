package br.com.joaogodoi.cart.controllers;

import br.com.joaogodoi.cart.entities.Client;
import br.com.joaogodoi.cart.entities.ShoppingCart;
import br.com.joaogodoi.cart.services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "/api/client")
@Tag(name = "Client", description = "Endpoints for managing clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    @Operation(summary = "List all client",
            description = "List all client",
            tags = {"Client"},
            responses = {@ApiResponse(description = "Success", responseCode = "200",
                                      content = {@Content(mediaType = "application/json", 
                                              array = @ArraySchema(schema = @Schema(implementation = Client.class)))}),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)})
    public ResponseEntity<List<Client>> findAll() throws Exception {
        List<Client> clients = clientService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(clients);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Get a client",
            description = "Get details of a client",
            tags = {"Client"},
            responses = {@ApiResponse(description = "Success", responseCode = "200",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Client.class))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)})
    public ResponseEntity<Client> findByCode(@PathVariable Long id) throws Exception {
        Client client = clientService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(client);
    }

    @PostMapping
    @Operation(summary = "Create a new client",
            description = "Create a new client",
            tags = {"Client"},
            responses = {@ApiResponse(description = "Created", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Client.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)})
    public ResponseEntity<Client> createClient(@RequestBody Client client) throws Exception {
        Client clientCreated = clientService.createClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientCreated);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Updates a client",
            description = "Updates a client",
            tags = {"Client"},
            responses = {@ApiResponse(description = "Updated", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Client.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)})
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client client) throws Exception {
        Client clientCreated = clientService.updateClient(id, client);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientCreated);
    }

    @Operation(summary = "Get a client's cart",
            description = "Get a client's cart'",
            tags = {"Client"},
            responses = {@ApiResponse(description = "Success", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = ShoppingCart.class))),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)})
    @GetMapping(value = "/{id}/cart")
    public ResponseEntity<ShoppingCart> getItemsOnCart(@PathVariable Long id) throws Exception {
        ShoppingCart shoppingCart = clientService.geShoppingCart(id);
        return ResponseEntity.status(HttpStatus.OK).body(shoppingCart);
    }

    @Operation(summary = "Add a product to a client's shopping cart",
            description = "Add a product shopping cart",
            tags = {"Client"},
            responses = {@ApiResponse(description = "Added", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Client.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)})
    @PostMapping(value = "/{clientId}/cart/add/{productCode}/{unitPrice}/{quantity}")
    public ResponseEntity<ShoppingCart> addProductOnCart(@PathVariable Long clientId, @PathVariable Long productCode,
                                                         @PathVariable String unitPrice, @PathVariable String quantity) throws Exception {
        ShoppingCart shoppingCart = clientService.addProductOnCart(clientId, productCode, new BigDecimal(unitPrice), Integer.parseInt(quantity));
        return ResponseEntity.status(HttpStatus.CREATED).body(shoppingCart);
    }
}
