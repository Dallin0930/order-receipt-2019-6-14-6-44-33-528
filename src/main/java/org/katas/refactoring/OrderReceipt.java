package org.katas.refactoring;

import java.util.stream.Collectors;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {
    private Order order;

    public OrderReceipt(Order o) {
        this.order = o;
    }



    public String printReceipt() {
        StringBuilder output = new StringBuilder();

        printHeaders(output);

        printCustomerNaneAndDress(output);

        printLineItems(output);


        final double tax= 0.1;
        double totalSaleTax=calculateTotalSaleTax(tax);
        printSaleTax(output,totalSaleTax);


        double totalLneItem=calculateTotalLineItem(tax);
        printTotalAmount(output,totalLneItem);

        return output.toString();



    }





    private void printTotalAmount(StringBuilder output, double totalLneItem) {
        output.append("Total Amount").append('\t').append(totalLneItem);
    }

    private double calculateTotalLineItem(double tax) {
    return order.getLineItems().stream().mapToDouble(item -> item.totalAmount()+item.totalAmount()*tax).sum();
    }

    private void printSaleTax(StringBuilder output, double totalSaleTax) {
        output.append("Sales Tax").append('\t').append(totalSaleTax);
    }

    private double calculateTotalSaleTax(double tax) {
         return order.getLineItems().stream().mapToDouble(item -> item.totalAmount() *  tax).sum();
    }

    private void printLineItems(StringBuilder output) {
        output.append(order.getLineItems().stream().map(items -> String.format("%s\t%.1f\t%d\t%.1f",items.getDescription(),items.getPrice(),items.getQuantity(),items.totalAmount()))
                .collect(Collectors.joining("\n")))      //格式
                .append("\n");
    }

    private void printCustomerNaneAndDress(StringBuilder output) {
        output.append(order.getCustomerName());
        output.append(order.getCustomerAddress());
    }

    private void printHeaders(StringBuilder output) {
        String header = "======Printing Orders======\n";
        output.append(header);
    }
}