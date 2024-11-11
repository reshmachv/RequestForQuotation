package com.ecommerce.RFQService.Model;

import java.util.Comparator;
import java.util.List;

public enum RfqStatus {
    CREATED(0), //The RFQ has been initiated and created by the buyer (customer). This is the starting point of the RFQ process.

    AWAITING_ACCEPTANCE(1), // The RFQ has been sent to suppliers, and the buyer is waiting for them to accept the request and begin preparing their quotations.

    IN_REVIEW(2), //The buyer or procurement team is reviewing the received quotations from suppliers. This status indicates that the evaluation process is ongoing.

    CLARIFICATION_REQUIRED(3), //This status typically indicates that the buyer has questions or needs more details from the supplier(s) about the quotation before proceeding further.

    QUOTED(4), //The supplier has provided their quotation or bid in response to the RFQ. It means the supplier has responded with a proposal.

    ACCEPTED(5), //The buyer has accepted one or more of the quotations. This indicates that an agreement or contract may be in the works or already finalized.

    SUPPLIER_REJECTED(6), // This status indicates that the supplier has declined to participate in the RFQ process, perhaps due to pricing, capacity, or other business reasons.

    REJECTED(7), //This could mean that the buyer has rejected the supplier's quotation (or a particular proposal) for various reasons, such as price, quality, or terms not meeting the buyer’s needs.

    CANCELLED(8),  //The RFQ process has been canceled by the buyer, potentially due to changes in requirements, priorities, or other external factors. No further action is expected.

    CLOSED(9);  //The RFQ process has been fully completed, either because a supplier was selected and a contract was finalized, or because the RFQ was not successful for other reasons (e.g., no satisfactory quotes were received).
    private final int priority;

    RfqStatus(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    // Static method to get the status with the highest priority
    public static RfqStatus getHighestPriorityStatus(List<RfqStatus> statuses) {
        return statuses.stream()
                .max(Comparator.comparingInt(RfqStatus::getPriority))
                .orElse(CREATED); // Default if no statuses exist
    }
}
