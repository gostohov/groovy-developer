package org.example

import org.junit.jupiter.api.Test
import static org.junit.jupiter.api.Assertions.*

class HelloOtusTest {
    
    @Test
    void testReverseList() {
        def helloOtus = new HelloOtus()
        
        // Перехватываем вывод в консоль
        def originalOut = System.out
        def byteArrayOut = new ByteArrayOutputStream()
        System.setOut(new PrintStream(byteArrayOut))
        
        // Выполняем метод
        helloOtus.main()
        
        // Восстанавливаем стандартный вывод
        System.setOut(originalOut)
        
        // Получаем результат вывода
        def output = byteArrayOut.toString()
        
        // Проверяем, что вывод содержит ожидаемые строки
        assertTrue(output.contains("Исходный список: [1, 2, 3, 4, 5]"))
        assertTrue(output.contains("Развернутый список с помощью guava: [5, 4, 3, 2, 1]"))
    }
}
