package com.ecommerce.RFQService.Scheduler;

public class RfqStatusUpdation {
/*    public RfqStatus getHighestPriorityStatus(List<SupplierRfqQuotations> suppliers) {
        return suppliers.stream()
                .map(SupplierRfqQuotations::getStatus)  // Extract status from each supplier
                .max(Comparator.comparingInt(RfqStatus::getPriority)) // Get the highest priority
                .orElse(RfqStatus.CREATED); // Default if no status exists
    }

    // Method to update the RfqItemStatus based on the highest priority supplier status
    public void updateRfqItemStatusBasedOnSupplierQuotations(QuotedItems rfqItem) {
        // Get the highest priority RfqStatus from the suppliers
        RfqStatus highestPriorityStatus = getHighestPriorityStatus(rfqItem.getSuppliers());

        // Update the RfqItemStatus based on the highest priority
        rfqItem.setRfqItemStatus(highestPriorityStatus.name());  // You can store the name or the enum value in the database
    }*/
}
