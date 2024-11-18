package com.springBoot.taskManager.Dto;

//@Getter
//@Setter
//@AllArgsConstructor
//public class TaskDTO {
//    public String title;
//    public String description;
//    public String deadline;
//}
public record TaskDTO(int id,String title, String description, String deadline,Boolean completed){}
