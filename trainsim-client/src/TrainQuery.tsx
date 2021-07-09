export default interface TrainQuery {
    source: string;
    target: string;
    depart: Date;
    return: Date | null;
    travelers: number;
};