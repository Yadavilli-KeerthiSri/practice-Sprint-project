package com.cg.exception;

import java.util.Date;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    // ------- Domain: not found (for your REST endpoints if any) -------
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> resourceNotFound(ResourceNotFoundException ex, WebRequest req) {
        ErrorDetails details = new ErrorDetails(ex.getMessage(), new Date(), req.getDescription(false));
        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }

    // ------- Data integrity (FK constraint violations on delete) -------
    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleFKViolation(DataIntegrityViolationException ex,
                                    HttpServletRequest request,
                                    RedirectAttributes ra) {

        String path = request.getRequestURI(); // e.g., "/admin/restaurants/delete/5"
        String msg;
        String redirect;

        if (path != null && path.contains("/admin/menu-items")) {
            msg = "Cannot delete this item because it’s part of one or more orders.";
            redirect = "redirect:/admin/menu-items";
        } else if (path != null && path.contains("/admin/restaurants")) {
            msg = "Cannot delete this restaurant: some of its menu items are used in existing orders.";
            redirect = "redirect:/admin/restaurants";
        } else {
            msg = "Delete blocked: this record is referenced by existing data.";
            // Fallback: send to a safe landing page
            redirect = "redirect:/admin/restaurants";
        }

        ra.addFlashAttribute("error", msg);
        return redirect;
    }

    // ------- Business rule violations you throw from services -------
    @ExceptionHandler(IllegalStateException.class)
    public String handleIllegalState(IllegalStateException ex, RedirectAttributes ra) {
        ra.addFlashAttribute("error", ex.getMessage());
        return "redirect:/admin/restaurants";
    }

    // ------- Safety net: any unexpected exception -> user-friendly flash -------
    @ExceptionHandler(Exception.class)
    public String handleAny(Exception ex, RedirectAttributes ra) {
        ra.addFlashAttribute("error", "We couldn’t complete that action. Please try again.");
        return "redirect:/admin/restaurants";
    }
}


//restaurantservice
@Transactional
    public void delete(Long restaurantId) {
        if (orderRepository.existsByItems_Restaurant_RestaurantId(restaurantId)) {
            throw new IllegalStateException("Cannot delete: this restaurant has items used in orders.");
        }
        restaurantRepository.deleteById(restaurantId);
    }



//restaurants.html --admin
    <!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Restaurant Directory | Admin</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://fonts.googleapis.com" rel="stylesheet">
    <style>
        body { font-family: 'Inter', sans-serif; }
    </style>
</head>
<body class="bg-slate-50 min-h-screen p-8">

    <div class="max-w-6xl mx-auto">
    <!-- ERROR MESSAGE -->
<div th:if="${error}"
     class="mb-4 rounded-xl border border-red-200 bg-red-50 text-red-700 px-4 py-3">
    <strong>Error:</strong>
    <span th:text="${error}"></span>
</div>
        <!-- Header Section -->
        <div class="flex flex-col md:flex-row justify-between items-start md:items-center mb-10 gap-4">
            <div>
                <h2 class="text-3xl font-extrabold text-slate-900 tracking-tight">Restaurant Partners</h2>
                <p class="text-slate-500 text-sm mt-1">Manage partner locations, cuisines, and public ratings.</p>
            </div>
            
            <a th:href="@{/admin/restaurants/add}" class="flex items-center gap-2 bg-orange-500 hover:bg-orange-600 text-white px-6 py-3 rounded-2xl font-bold shadow-lg shadow-orange-200 transition-all transform active:scale-95">
                <span class="text-lg">➕</span> Add New Restaurant
            </a>
        </div>

        <!-- Table Card -->
        <div class="bg-white rounded-3xl shadow-sm border border-slate-200 overflow-hidden">
            <div class="overflow-x-auto">
                <table class="w-full text-left border-collapse">
                    <thead>
                        <tr class="bg-orange-50/50 border-b border-slate-100">
                            <th class="px-6 py-5 text-xs font-bold uppercase tracking-widest text-orange-700">Establishment</th>
                            <th class="px-6 py-5 text-xs font-bold uppercase tracking-widest text-orange-700">Location</th>
                            <th class="px-6 py-5 text-xs font-bold uppercase tracking-widest text-orange-700">Cuisine</th>
                            <th class="px-6 py-5 text-xs font-bold uppercase tracking-widest text-orange-700">Rating</th>
                            <th class="px-6 py-5 text-xs font-bold uppercase tracking-widest text-orange-700 text-center">Actions</th>
                        </tr>
                    </thead>
                    <tbody class="divide-y divide-slate-100">
                        <tr th:each="r : ${restaurants}" class="hover:bg-orange-50/30 transition-colors group">
                            <!-- Name -->
                            <td class="px-6 py-5">
                                <div class="flex items-center gap-3">
                                    <div class="w-10 h-10 rounded-xl bg-orange-100 flex items-center justify-center text-orange-600 font-bold">
                                        <span th:text="${#strings.substring(r.restaurantName,0,1)}"></span>
                                    </div>
                                    <span class="font-bold text-slate-800 text-lg" th:text="${r.restaurantName}"></span>
                                </div>
                            </td>
                            
                            <!-- Location -->
                            <td class="px-6 py-5">
                                <div class="flex items-center text-slate-600">
                                    <span class="mr-2">📍</span>
                                    <span th:text="${r.location}"></span>
                                </div>
                            </td>

                            <!-- Cuisine -->
                            <td class="px-6 py-5">
                                <span class="bg-amber-50 text-amber-700 px-3 py-1 rounded-lg text-xs font-bold border border-amber-100" th:text="${r.cuisine}"></span>
                            </td>

                            <!-- Rating -->
                            <td class="px-6 py-5">
                                <div class="flex items-center gap-1.5">
                                    <span class="text-orange-500 font-bold" th:text="${r.ratings}"></span>
                                    <span class="text-orange-400 text-xs">★</span>
                                </div>
                            </td>

                            <!-- Actions -->
                            <td class="px-6 py-5">
                                <div class="flex justify-center items-center gap-4">
                                    <a th:href="@{/admin/restaurants/edit/{id}(id=${r.restaurantId})}" 
                                       class="text-sm font-bold text-blue-600 hover:text-blue-800 transition-colors">
                                        Edit
                                    </a>
                                    <div class="h-4 w-px bg-slate-200"></div>
                                    <a th:href="@{/admin/restaurants/delete/{id}(id=${r.restaurantId})}" 
                                       onclick="return confirm('Are you sure you want to remove this restaurant?')"
                                       class="text-sm font-bold text-red-400 hover:text-red-600 transition-colors">
                                        Delete
                                    </a>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>

            <!-- Empty State -->
            <div th:if="${#lists.isEmpty(restaurants)}" class="py-24 text-center">
                <div class="text-5xl mb-4">🏪</div>
                <h3 class="text-slate-800 font-bold text-xl">No restaurants yet</h3>
                <p class="text-slate-500 max-w-xs mx-auto mt-2">Your directory is empty. Add your first partner restaurant to get started.</p>
            </div>
        </div>

        <!-- Dashboard Link -->
        <div class="mt-10 flex justify-center">
            <a th:href="@{/admin/dashboard}" class="group flex items-center gap-2 text-slate-400 hover:text-orange-600 font-semibold transition-all">
                <svg class="w-4 h-4 transform group-hover:-translate-x-1 transition-transform" fill="none" stroke="currentColor" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7"></path></svg>
                Return to Admin Home
            </a>
        </div>
    </div>

</body>
</html>


//menurepo
boolean existsByRestaurant_RestaurantId(Long restaurantId);



//menuitemservice
    @Override
    @Transactional
    public void delete(Long id) {
        if (orderRepository.existsByItems_ItemId(id)) {
            throw new IllegalStateException("Cannot delete: item is used in existing orders.");
        }
        menuItemRepository.deleteById(id);
    }


//orderrepo
    boolean existsByItems_ItemId(Long itemId);                        // Order -> items -> itemId
	    boolean existsByItems_Restaurant_RestaurantId(Long restaurantId);


//adminrestcontr

@GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        restaurantService.delete(id); // If FK violation happens, GlobalExceptionHandler handles it
        ra.addFlashAttribute("success", "Restaurant deleted successfully.");
        return "redirect:/admin/restaurants";
    }
